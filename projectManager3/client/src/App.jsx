import "./App.css";
import { React, useState } from "react";
import ProductForm from "./components/ProductForm";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import DisplayOne from "./components/DisplayOne";
import UpdateProduct from "./components/UpdateProduct";

function App() {
  return (
    <>
      <h1>Product Manager</h1>
      <BrowserRouter>
        <Routes>
          <Route index element={<ProductForm />} />
          <Route path="/oneProduct/:id" element={<DisplayOne />} />
          <Route path="/updateProduct/:id" element={<UpdateProduct />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
