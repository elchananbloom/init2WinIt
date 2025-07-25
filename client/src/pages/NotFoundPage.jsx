import { Link } from "react-router-dom";
import Page from "../components/Page";


const NotFoundPage = () => {
    return (
        <Page>
           <div className="container d-flex flex-column align-items-center justify-content-center" style={{ minHeight: "70vh" }}>
      <div className="text-center">
        <h1 className="display-4 mb-3">404</h1>
        <h2 className="mb-3">Page Not Found</h2>
        <p className="text-muted mb-4">
          Sorry, the page you're looking for doesn’t exist or has been moved.
        </p>
        <Link to="/" className="btn btn-outline-dark">
          Back to Dashboard
        </Link>
      </div>
    </div>
        </Page>
    )
}

export default NotFoundPage;