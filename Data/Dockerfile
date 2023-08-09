# 베이스 이미지 설정
FROM python:3.11.4

# 소스 코드 및 필요한 파일 복사
COPY . /app

# 작업 디렉토리 설정
WORKDIR /app

# 가상 환경 생성 및 활성화, 필요한 패키지 설치
RUN python -m venv myvenv && \
    . myvenv/bin/activate && \
    pip install --no-cache-dir -r requirements.txt

# 컨테이너 실행 명령어 설정
CMD ["python", "manage.py", "runserver", "0.0.0.0:8000"]

# 컨테이너가 외부와 통신할 수 있도록 8000번 포트 노출
EXPOSE 8000