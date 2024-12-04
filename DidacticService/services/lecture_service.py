from typing import Dict, Any

import httpx
from fastapi import HTTPException
from pymongo import ReturnDocument

from models.lecture import Lecture
from database.mongodb import db


async def verify_lecture_mariadb(lecture_code:str):
    mariadb_url = f"http://localhost:8080/api/MoodleApp/lectures/{lecture_code}"

    async with httpx.AsyncClient() as client:
        response = await client.get(mariadb_url)
        if response.status_code == 404:
            raise HTTPException(status_code=404, detail=f"Lecture code {lecture_code} not found")

        print(response)
        return response.json()


class LectureService:
    def __init__(self):
        self.collection = db["didactic_materials"]

    async def get_all_lectures(self):
        lectures = []
        async for discipline in self.collection.find():
            lectures.append(Lecture(**discipline))
        return lectures

    async def get_lecture_by_code(self, lecture_code:str):
        lecture = await self.collection.find_one({"lecture_code": lecture_code})

        if lecture:
            return Lecture(**lecture)
        return None

    async def create_lecture(self, lecture: Lecture):
        await verify_lecture_mariadb(lecture.lecture_code)

        existing_lecture = await self.collection.find_one({"lecture_code": lecture.lecture_code})
        if existing_lecture:
            raise HTTPException(status_code=400, detail=f"Lecture code {lecture.lecture_code} already exists")

        await self.collection.insert_one(lecture.model_dump())

        return lecture

    async def update_partial_lecture(self, lecture_code: str, updates: Dict[str, Any]):

        await verify_lecture_mariadb(lecture_code)

        lecture = await self.collection.find_one({"lecture_code": lecture_code})
        if not lecture:
            raise HTTPException(status_code=404, detail=f"Lecture code {lecture_code} not found in MongoDB")

        if "lecture_code" in updates and updates["lecture_code"] != lecture_code:
            await verify_lecture_mariadb(updates["lecture_code"])

            existing_lecture = await self.collection.find_one({"lecture_code": updates["lecture_code"]})
            if existing_lecture:
                raise HTTPException(status_code=400,
                                    detail=f"Lecture code {updates['lecture_code']} already exists in MongoDB")

        updated_lecture = await self.collection.find_one_and_update(
            {"lecture_code": lecture_code},
            {"$set": updates},
            return_document=ReturnDocument.AFTER
        )

        if not updated_lecture:
            raise HTTPException(status_code=500, detail="Failed to update the lecture in MongoDB")

        return Lecture(**updated_lecture)
