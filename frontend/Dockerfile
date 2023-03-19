# Use an official Node.js runtime as a parent image
FROM node:19-alpine

# Set the working directory to /app
WORKDIR /app

# Copy package.json and package-lock.json to the container
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy all other application files to the container
COPY . .

# Expose port 80
EXPOSE 80

# Start the application
CMD ["npm", "start"]