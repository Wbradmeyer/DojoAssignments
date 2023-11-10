import { React, useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";

const UpdateProduct = () => {
  const navigate = useNavigate();
  const { id } = useParams();
  const [product, setProduct] = useState({
    title: "",
    price: 0,
    description: "",
  });

  useEffect(() => {
    axios
      .get(`http://localhost:8000/api/oneProduct/${id}`)
      .then((res) => {
        console.log(res);
        setProduct(res.data);
      })
      .catch((err) => console.log(err));
  }, []);

  const handleVals = (e) => {
    setProduct({ ...product, [e.target.name]: e.target.value });
  };

  const onSubmitHandler = (e) => {
    e.preventDefault();
    axios
      .patch(`http://localhost:8000/api/updateProduct/${id}`, product)
      .then((res) => {
        console.log(res);
        navigate("/");
      })
      .catch((err) => console.log(err));
  };

  return (
    <div>
      <form onSubmit={onSubmitHandler}>
        <p>
          <label>Title: </label>
          <input
            type="text"
            name="title"
            value={product.title}
            onChange={handleVals}
          />
        </p>
        <p>
          <label>Price: </label>
          <input
            type="number"
            name="price"
            value={product.price}
            onChange={handleVals}
          />
        </p>
        <p>
          <label>Description: </label>
          <input
            type="text"
            name="description"
            value={product.description}
            onChange={handleVals}
          />
        </p>
        <button style={{ backgroundColor: "lightBlue" }}>Update</button>
      </form>
    </div>
  );
};

export default UpdateProduct;
