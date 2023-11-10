const Product = require('../models/product.model')

module.exports = {
    getAllProducts: (req, res) => {
        Product.find({})
            .then((product) => {
                res.json(product)
            })
            .catch((err) => {
                res.json({ message: 'Error in find all', 
                error: err })
            })
    },

    createProduct: (req, res) => {
        Product.create(req.body)
            .then(newProduct => res.json({ product: newProduct }))
            .catch(err => res.json({ message: 'Error in create', error: err }));
    },

    getOneProduct: (req, res) => {
        Product.findOne({_id:req.params.id})
            .then(thisProduct => res.json(thisProduct))
            .catch(err => res.json({ message: 'Error in find one', error: err }));
    },

    updateProduct: (req, res) => {
        Product.findOneAndUpdate({_id:req.params.id}, req.body, {new:true})
            .then(updated => res.json(updated))
            .catch(err => res.json({ message: 'Error in update', error: err }))
    },

    deleteProduct: (req, res) => {
        Product.deleteOne({_id:req.params.id})
            .then(deleted => res.json(deleted))
            .catch(err => res.json({ message: 'Error in delete', error: err }))
    }
}