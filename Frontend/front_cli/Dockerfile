# build stage
FROM node:18 as build-stage
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build
 
# production stage
FROM nginx:stable-alpine as production-stage
COPY --from=build-stage /app/dist /usr/share/nginx/html



# Copy nginx configuration file
# COPY ./nginx.conf /etc/nginx/conf.d/default.conf

# Nginx의 default.conf 삭제
RUN rm /etc/nginx/conf.d/default.conf

#host pc의 nginx.conf를 해당 경로에 복사
COPY ./nginx.conf /etc/nginx/conf.d

#80 포트 오픈
EXPOSE 80
