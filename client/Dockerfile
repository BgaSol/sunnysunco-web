# 第一阶段：构建
FROM node:20-alpine AS builder

# 设置工作目录
WORKDIR /app

# 安装 pnpm
RUN npm install -g pnpm

# 复制源代码 除node_modules
COPY . .
RUN rm -rf node_modules
# 安装依赖
RUN pnpm install

# 构建前端应用
RUN pnpm run build

# 第二阶段：部署
FROM nginx:alpine

# 复制构建好的文件到 nginx 的默认目录
COPY --from=builder /app/dist /usr/share/nginx/html
# 配置 nginx 配置文件
COPY nginx.conf /etc/nginx/nginx.conf

# 启动 nginx 服务器
CMD ["nginx", "-g", "daemon off;"]

# 暴露端口
EXPOSE 3000
