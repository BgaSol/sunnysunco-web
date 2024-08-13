# 使用 Maven 官方镜像进行构建
FROM maven:3-amazoncorretto-11 AS build

# 设置工作目录
WORKDIR /app

COPY . .

# 使用 Maven 打包项目
RUN mvn clean package -DskipTests

FROM amazoncorretto:11

# 设置工作目录
WORKDIR /app

# 从构建阶段复制打包后的 jar 文件到当前镜像
COPY --from=build /app/target/*.jar app.jar

# 设置应用的入口点
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=prod"]
# 暴露应用使用的端口
EXPOSE 8080