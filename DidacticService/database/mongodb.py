from typing import Any, Mapping

from motor.motor_asyncio import AsyncIOMotorClient, AsyncIOMotorCollection

client = AsyncIOMotorClient("mongodb://localhost:27017")
db = client["disciplines_data"]

def get_mongo_collection() -> AsyncIOMotorCollection[Mapping[str, Any] | Any]:
    return db["didactic_materials"]
