# build stage
FROM node:18 as build-stage
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build
 
# production stage
FROM nginx:stable-alpine as production-stage
