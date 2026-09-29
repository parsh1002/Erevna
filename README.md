####🧠 Erevna

An AI-powered document knowledge and question-answering system built using Java, Spring Boot, Spring AI, PostgreSQL, pgvector, and Ollama.

Erevna allows users to upload PDF documents, convert their contents into vector embeddings, retrieve relevant information using semantic search, and generate context-aware answers using a local LLM.

## 🚀 Features

📄 Document Processing

PDF document upload

Text extraction

Fixed-size text chunking

Overlapping chunks for better context preservation

🧠 AI-Powered Search

Local embedding generation using nomic-embed-text

768-dimensional vector embeddings

Semantic similarity search using pgvector

Top-K relevant chunk retrieval

💬 RAG-Based Question Answering

Converts user questions into embeddings

Retrieves relevant document context

Builds a context-aware prompt

Generates answers using llama3.2

🗄️ Vector Database

PostgreSQL integration

pgvector extension

Stores document chunks and embeddings

⚡ Performance Tracking

Document ingestion timing

Embedding generation timing

Vector retrieval latency

End-to-end retrieval measurements

🧱 Tech Stack

Backend: Java, Spring Boot

AI Framework: Spring AI

LLM: Ollama + llama3.2

Embeddings: Ollama + nomic-embed-text

Database: PostgreSQL

Vector Search: pgvector

ORM: Spring Data JPA / Hibernate

Document Processing: PDFBox

Build Tool: Maven

Containerization: Docker Compose

Caching / Infrastructure: Redis

## 📁 Project Structure : 

<img width="567" height="627" alt="image" src="https://github.com/user-attachments/assets/c73ba37d-7dfc-429c-95ae-b9357f50b1de" />


## ⚙️ How Erevna Works

1️⃣ Upload Document

A PDF is uploaded through the document API.

PDF
 ↓
Text Extraction
 ↓
Text Chunking

The current chunk configuration is:

document:
  chunk:
    size: 1000
    overlap: 200

2️⃣ Generate Embeddings

Each document chunk is converted into a vector embedding using:

nomic-embed-text

The embeddings are stored in PostgreSQL using pgvector.

3️⃣ Ask a Question

The user sends a question to the chat API:

Question
 ↓
Query Embedding
 ↓
pgvector Similarity Search
 ↓
Top 5 Relevant Chunks

4️⃣ Generate the Answer

The retrieved chunks are added as context to the prompt and sent to:

llama3.2

The model then generates the final response.

Relevant Context
       +
User Question
       ↓
Prompt Construction
       ↓
Ollama / llama3.2
       ↓
Generated Answer

## 📡 API Endpoints

📄 Documents

POST

/api/documents/upload

Upload and process a PDF document

Example:

curl -X POST \
  http://localhost:8080/api/documents/upload \
  -F "file=@document.pdf"

## 💬 Chat

POST

/api/chat/ask

Ask a question using the indexed documents

Request:

{
  "question": "What is the difference between MPI_Reduce and MPI_Allreduce?"
}

The API generates an embedding for the question, retrieves relevant chunks using pgvector, and uses the retrieved context to generate the answer.

## 🧠 Embeddings

POST

/api/embedding/test

Generate and inspect an embedding

## 🧪 Performance

Erevna was benchmarked locally to evaluate document ingestion and semantic retrieval.

Semantic Retrieval

Tested across 40+ queries:

Average retrieval latency: 38.7 ms

P95 retrieval latency: 48.5 ms

RAG Answer Quality

A 40-query evaluation set produced:

74% answer accuracy

84% hit rate

Document Ingestion

A benchmark document containing approximately 83K characters was processed into:

105 searchable chunks

19.2 seconds total ingestion time

These are local development measurements and can vary depending on hardware, model inference speed, document size, and database state.

## 🔧 Local Setup

1️⃣ Clone the Repository

git clone https://github.com/parsh1002/Erevna.git
cd Erevna

2️⃣ Start PostgreSQL

Start the PostgreSQL + pgvector setup using Docker Compose:

docker compose up -d

The application is configured to connect to:

Host: localhost
Port: 5433
Database: erevna

3️⃣ Install Ollama Models

Install Ollama and pull the required models:

ollama pull llama3.2
ollama pull nomic-embed-text

Make sure Ollama is running on:

http://localhost:11434

4️⃣ Configure Environment Variables

Configure your database credentials through environment variables rather than committing them to Git:

DB_USERNAME=your_username
DB_PASSWORD=your_password

5️⃣ Run the Application

Linux/macOS:

./mvnw spring-boot:run

Windows:

mvnw.cmd spring-boot:run

The application starts on:

http://localhost:8080

## 🧠 Core Logic Highlights

Fixed-size document chunking with overlap

Local embedding generation using Ollama

768-dimensional vector storage with pgvector

Cosine-distance-based semantic retrieval

Top-K context retrieval before response generation

RAG pipeline for document-grounded question answering

Local LLM inference without requiring an external LLM API

## ⚠️ Important Notes

Ollama must be running locally for embeddings and chat generation.

PostgreSQL must have the pgvector extension enabled.

Database credentials should be supplied through environment variables.

LLM response time depends heavily on local hardware and model inference.

The current retrieval pipeline uses a fixed Top-K value.

## 🚀 Future Improvements

Dockerize the complete application stack

Add GitHub Actions CI/CD

Deploy the application to AWS

Improve chunking using semantic/document-aware strategies

Add user authentication and document ownership

Add conversation history

Introduce asynchronous document processing

Add automated retrieval and answer evaluation

Improve scalability for larger document collections

## 💡 What This Project Demonstrates

Building a production-oriented backend using Spring Boot

Designing a complete RAG pipeline

Working with vector databases and semantic search

Integrating local LLMs into backend applications

Processing and indexing unstructured documents

Measuring backend and retrieval performance

Designing clean separation between controllers, services, repositories, and processing components

## 👨‍💻 Author

Parshva Kumar J Jain

GitHub: https://github.com/parsh1002
