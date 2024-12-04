from typing import List

from pydantic import BaseModel, Field


class Evaluation(BaseModel):
    type: str
    weight: float

class Lecture(BaseModel):
    lecture_code: str
    evaluation_methods: List[Evaluation]

    class Config:
        extra = 'ignore'