import { React, useEffect, useState } from "react";
import axios from "axios";
import { useNavigate, Link } from "react-router-dom";

const ProductForm = () => {
  const navigate = useNavigate();
  const [allProducts, setAllProducts] = useState([]);
  const [product, setProduct] = useState({
    title: "",
    price: 0,
    description: "",
  });

  useEffect(() => {
    axios
      .get("http://localhost:8000/api/products")
      .then((res) => {
        console.log(res);
        setAllProducts(res.data);
      })
      .catch((err) => console.log(err));
  }, []);

  const handleVals = (e) => {
    setProduct({ ...product, [e.target.name]: e.target.value });
  };

  const onSubmitHandler = (e) => {
    // e.preventDefault();
    axios
      .post("http://localhost:8000/api/product", product)
      .then((res) => {
        console.log(res);
        console.log(res.data);
      })
      .catch((err) => console.log(err));
    navigate("/");
  };

  const handleDelete = (e, id) => {
    axios
      .delete(`http://localhost:8000/api/deleteProduct/${id}`)
      .then((res) => {
        console.log(res);
        const newList = allProducts.filter((product) => product._id !== id);
        setAllProducts(newList);
      })
      .catch((err) => console.log(err));
    navigate("/");
  };

  return (
    <div>
      <div>
        <form onSubmit={onSubmitHandler}>
          <p>
            <label>Title: </label>
            <input type="text" name="title" onChange={handleVals} />
          </p>
          <p>
            <label>Price: </label>
            <input type="number" name="price" onChange={handleVals} />
          </p>
          <p>
            <label>Description: </label>
            <input type="text" name="description" onChange={handleVals} />
          </p>
          <button style={{ backgroundColor: "lightBlue" }}>Submit</button>
        </form>
      </div>
      <p>***********************************************</p>
      <div>
        <h1>All Products:</h1>
        {allProducts.map((thisProduct) => (
          <div key={thisProduct._id}>
            <h2>{thisProduct.title}</h2>
            <p>${thisProduct.price}</p>
            <div>
              <Link to={`/oneProduct/${thisProduct._id}`}>Details |</Link>
              <Link to={`/updateProduct/${thisProduct._id}`}> Update </Link>
              <button
                onClick={(e) => handleDelete(e, thisProduct._id)}
                style={{ backgroundColor: "red" }}
              >
                Delete
              </button>
            </div>
            <p>------------------</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default ProductForm;
