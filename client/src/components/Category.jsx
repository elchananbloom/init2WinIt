import { useState } from "react";
import Modal from "./Modal";
import CategoryFormModal from "./CategoryFormModal";

const url = 'http://localhost:8080/api/';


const Category = ({ category, fetchCategories}) => {
    const [showModal, setShowModal] = useState(false);

    const handleShowModal = (show) => {
        setShowModal(show);
    };

    const deleteCategory = (id) => {
        const options = {
            method: 'DELETE'
        };
        fetch(`${url}transaction/category/${category.transactionCategoryId}`, options)
            .then(response => {
                if (response.status === 204) {
                    fetchCategories();
                } else {
                    return Promise.reject(`Unexpected Error, Status Code: ${response.status}`);
                }
            })
            .catch(console.log);
    }

    const handleDelete = () => {
        if (window.confirm(`Are you sure you want to delete Category: ${category.transactionCategoryName}?`)) {
            deleteCategory();
        }
    }
    return (
        <div
            key={category.transactionCategoryId}
            className="d-flex justify-content-between align-items-center bg-white border rounded w-50 p-3 mb-3"
        >
            <span>{category.transactionCategoryName}</span>
            <div>
                <button onClick={() => handleShowModal(true)} className="btn btn-outline-dark mr-2">Edit</button>
                <button onClick={handleDelete} className="btn btn-danger">Delete</button>
            </div>
            <Modal
                show={showModal}
                onClose={() => setShowModal(false)}
                title={'Edit Category'}
            >
                <CategoryFormModal category={category} handleShowModal={handleShowModal} fetchCategories={fetchCategories}/>
            </Modal>
        </div>
    );
}

export default Category;