import { useEffect, useState } from "react";
import Page from "../components/Page"
import { useNavigate } from "react-router-dom";
import Category from "../components/Category";
import Modal from "../components/Modal";
import CategoryFormModal from "../components/CategoryFormModal";

const url = 'http://localhost:8080/api/';


const CategoriesPage = () => {
    const [categories, setCategories] = useState([]);
    const navigate = useNavigate();
    const [showModal, setShowModal] = useState(false);

    const fetchCategories = () => {
        fetch(`${url}/transaction/category`)
            .then(res => {
                if (res.status === 200) {
                    return res.json();
                }
                if (res.status === 403) {
                    navigate('/login');
                }
            })
            .then(data => {
                console.log(data);
                setCategories(data);
            });
    }

    useEffect(() => {
        fetchCategories();
    }, [])

    const handleShowModal = (show) => {
        setShowModal(show);
    }

    return (
        <Page>
            {categories.map(cat => {
                return <Category category={cat}  handleShowModal={handleShowModal} fetchCategories={fetchCategories}/>
            })}
            <Modal
                show={showModal}
                onClose={() => setShowModal(false)}
                title={'Add Category'}
            >
                <CategoryFormModal handleShowModal={handleShowModal} fetchCategories={fetchCategories}/>
            </Modal>
            <div className="floating-button shadow">
                <button onClick={() => handleShowModal(true)} className="btn btn-primary">Add Category</button>
            </div>
        </Page>
    );
}

export default CategoriesPage;