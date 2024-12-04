from fastapi import FastAPI
from controllers.lecture_controller import router as discipline_router

app = FastAPI()

app.include_router(discipline_router, prefix="/api/academia", tags=["Disciplines"])
