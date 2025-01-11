내 프로젝트 + mysql
docker-compose 의 network를 활용, 두 컨테이너를 하나로 묶는다

깃허브 액션 사용
CI,CD 파일 작성

코드를 main에 push할때 실행.
코드 업데이트 시,
### CI
1. 깃허브 체크아웃
2. JDK 17 설정
3. application.yaml 파일 생성
4. Gradle 캐싱
5. Gradle 실행 권한 부여
6. Gradle로 프로젝트 빌드 (테스트 제외)
7. 테스트 코드 실행
8. 단위 테스트 결과 게시
9. 테스트 보고서 작성 후 게시

### CD
1. 깃허브 체크아웃
2. JDK 17 설정
3. Gradle 캐싱
4. Gradle 실행 권한 부여
5. Gradle로 프로젝트 빌드 (테스트 제외)
6. Docker 이미지 빌드 및 푸시
7. .env 파일 생성
8. .env 파일 EC2 서버로 전송
9. docker-compose.yml EC2 서버로 전송
10. Dockerfile EC2 서버로 전송
11. 빌드된 .jar 파일 EC2 서버로 전송
12. Docker-compose를 이용해 배포

### CI/CD 구현 후,
aws의 rds와 연동시킨다.
이제 db를 도커 허브에서 mysql 이미지를 받아서 컨테이너로 쓰는게 아니라,
aws의 rds에 mysql을 설치해서 사용한다.
docker-compose에서 mysql을 제거한다
application.yml에서 사용하는 db 정보를 내 이미지 mysql에서 rds의 mysql url로 변경한다
