import { useContext, useState } from "react";
import { useNavigate } from "react-router-dom";
import TokenContext from "../contexts/TokenContext";

const DEFAULT_CATEGORY = {
    transactionCategoryId: 0,
    transactionCategoryName: ''
}
const url = 'http://localhost:8080/api/';


const CategoryFormModal = ({ category = DEFAULT_CATEGORY, handleShowModal, fetchCategories }) => {
    const [name, setName] = useState(category.transactionCategoryName);
    const navigate = useNavigate();
    const {token } = useContext(TokenContext);

    const handleSubmit = (event) => {
        event.preventDefault();
        if (category.transactionCategoryId !== 0) {
            handleEdit();
        } else {
            handleAdd();
        }
    };

    const handleEdit = () => {
        category.transactionCategoryName = name;
        const options = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify(category)
        };

        fetch(`${url}transaction/category/${category.transactionCategoryId}`, options)
            .then(response => {
                if (response.status === 204) {
                    return category;
                } else if (response.status === 400) {
                    return response.json();
                } else if (response.status === 403) {
                    navigate('/');
                } else {
                    return Promise.reject(`Unexpected Error, Status Code: ${response.status}`);
                }
            })
            .then(data => {
                if (data.transactionCategoryId) {
                    fetchCategories();
                    handleShowModal(false);
                }
            })
            .catch(console.log);
    }

    const handleAdd = () => {
        category.transactionCategoryName = name;
        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify(category)
        };

        fetch(`${url}transaction/category`, options)
            .then(response => {
                if (response.status === 201 || response.status === 400) {
                    return response.json();
                } else if (response.status === 403) {
                    navigate('/');
                } else {
                    return Promise.reject(`Unexpected Error, Status Code: ${response.status}`);
                }
            })
            .then(data => {
                if (data.transactionCategoryId) {
                    fetchCategories();
                    handleShowModal(false);
                }
            })
            .catch(console.log);
    }

    const handleChange = (event) => {
        setName(event.target.value);
    };
    return (
        <>
            <form id="form" className="form-col" onSubmit={handleSubmit}>
                <fieldset className="form-group">
                    <label htmlFor="name">Name</label>
                    <input onChange={handleChange} className="form-control" type="text" name='name' value={name} id="name" required />
                </fieldset>
                <button className=" m-1 btn btn-primary" id="submit-form" type="submit">
                    {category.transactionCategoryId !== 0 ? 'Edit' : 'Add'}
                </button>
                <button onClick={() => handleShowModal(false)} className="m-1 btn btn-danger" type="button">Cancel</button>
            </form>
        </>
    );
}

export default CategoryFormModal;