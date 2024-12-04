from typing import Dict, Any

from fastapi import APIRouter, HTTPException
from models.lecture import Lecture
from services.lecture_service import LectureService

router = APIRouter()
lecture_service = LectureService()


###         GET         ###
@router.get("/disciplines", response_model=list[Lecture])
async def get_all_lectures():
    return await lecture_service.get_all_lectures()

@router.get("/disciplines/{lecture_code}", response_model=Lecture)
async def get_lecture_by_code(lecture_code: str):
    lecture = await lecture_service.get_lecture_by_code(lecture_code)

    if lecture:
        return lecture
    raise HTTPException(status_code=404, detail="Lecture not found")

###         POST            ###

@router.post("/", response_model=Lecture)
async def create_lecture(lecture: Lecture):
    return await lecture_service.create_lecture(lecture)

###         PATCH           ###
@router.patch("/disciplines/{lecture_code}")
async def update_partial_lecture(lecture_code: str, updates: Dict[str, Any]):
    return await lecture_service.update_partial_lecture(lecture_code, updates)