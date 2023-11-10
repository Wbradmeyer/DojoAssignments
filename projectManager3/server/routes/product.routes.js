const ProductController = require('../controllers/product.controller')

module.exports = (app) => {
    app.get('/api/products', ProductController.getAllProducts);
    app.post('/api/product', ProductController.createProduct)
    app.get('/api/oneProduct/:id', ProductController.getOneProduct)
    app.patch('/api/updateProduct/:id', ProductController.updateProduct)
    app.delete('/api/deleteProduct/:id', ProductController.deleteProduct)
}