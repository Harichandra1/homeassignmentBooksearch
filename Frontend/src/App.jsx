import { useState } from "react";
import axios from "axios";

function App() {
  const [title, setTitle] = useState("");
  const [author, setAuthor] = useState("");
  const [books, setBooks] = useState([]);
  const [error, setError] = useState("");

  const handleSearch = async (e) => {
    e.preventDefault();
    setError("");
    setBooks([]);

    try {
      const response = await axios.post("http://localhost:8080/Book/search", {
        title,
        author,
      });

      setBooks(response.data.books || []);
    } catch (err) {
      console.error(err);
      setError("Something went wrong. Please try again.");
    }
  };

  return (
    <div style={{ fontFamily: "Arial", padding: "20px" }}>
      <h1 style={{ marginBottom: "10px" }}>👋 Hi Alex!</h1>
      <h2 style={{ marginBottom: "20px" }}>
        📚 Welcome to your Book Finder
      </h2>

      <form onSubmit={handleSearch} style={{ marginBottom: "20px" }}>
        <div>
          <label>
            Title (required):{" "}
            <input
              type="text"
              value={title}
              onChange={(e) => setTitle(e.target.value)}
              required
              style={{ marginLeft: "10px" }}
            />
          </label>
        </div>
        <div style={{ marginTop: "10px" }}>
          <label>
            Author (optional):{" "}
            <input
              type="text"
              value={author}
              onChange={(e) => setAuthor(e.target.value)}
              style={{ marginLeft: "10px" }}
            />
          </label>
        </div>
        <button
          type="submit"
          style={{
            marginTop: "15px",
            padding: "8px 15px",
            background: "#007bff",
            color: "#fff",
            border: "none",
            borderRadius: "5px",
            cursor: "pointer",
          }}
        >
          Search
        </button>
      </form>

      {error && <p style={{ color: "red" }}>{error}</p>}

      <div>
        {books.length > 0 ? (
          books.map((book, index) => (
            <div
              key={index}
              style={{
                border: "1px solid #ccc",
                borderRadius: "8px",
                padding: "10px",
                marginBottom: "10px",
                display: "flex",
                gap: "10px",
              }}
            >
              {book.coverImageUrl ? (
                <img
                  src={book.coverImageUrl}
                  alt={book.title}
                  style={{ width: "80px", height: "auto", borderRadius: "4px" }}
                />
              ) : (
                <div
                  style={{
                    width: "80px",
                    height: "120px",
                    background: "#eee",
                    display: "flex",
                    alignItems: "center",
                    justifyContent: "center",
                    fontSize: "12px",
                    borderRadius: "4px",
                  }}
                >
                  No Cover
                </div>
              )}
              <div>
                <h4>{book.title}</h4>
                <p>
                  <strong>Author(s):</strong>{" "}
                  {book.authors?.join(", ") || "Unknown"}
                </p>
                <p>
                  <strong>First Published:</strong>{" "}
                  {book.firstPublishYear || "N/A"}
                </p>
              </div>
            </div>
          ))
        ) : (
          <p>No books found yet. Try searching!</p>
        )}
      </div>
    </div>
  );
}

export default App;
