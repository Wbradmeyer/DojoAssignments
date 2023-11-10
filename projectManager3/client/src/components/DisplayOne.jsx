import { React, useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";

const DisplayOne = () => {
  const navigate = useNavigate();
  const { id } = useParams();
  const [thisProduct, setThisProduct] = useState({});

  useEffect(() => {
    axios
      .get(`http://localhost:8000/api/oneProduct/${id}`)
      .then((res) => {
        console.log(res);
        setThisProduct(res.data);
      })
      .catch((err) => console.log(err));
  }, []);

  const handleDelete = () => {
    axios
      .delete(`http://localhost:8000/api/deleteProduct/${id}`)
      .then((res) => console.log(res))
      .catch((err) => console.log(err));
    navigate("/");
  };

  return (
    <div>
      <h2>Product: {thisProduct.title}</h2>
      <p>Price: ${thisProduct.price}</p>
      <p>Description: {thisProduct.description}</p>
      <button onClick={handleDelete} style={{ backgroundColor: "red" }}>
        Delete
      </button>
    </div>
  );
};

export default DisplayOne;
