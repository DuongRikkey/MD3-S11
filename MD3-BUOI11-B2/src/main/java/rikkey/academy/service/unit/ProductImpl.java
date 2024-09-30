package rikkey.academy.service.unit;

import rikkey.academy.model.Category;
import rikkey.academy.model.Product;
import rikkey.academy.service.IGenericDesign;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ProductImpl implements IGenericDesign <Product,Integer> {
   public static List<Product> productList = new ArrayList<>();
    static{
//        productList.add(new Product(1,"Iphone",10,11,CategoryImpl.categoryList.get(0)));


    }
    @Override
    public void addAndUpdate(Product product) {
        int index = findIndexByID(product.getId());
        if (index !=-1){
            Product oldProduct = productList.get(index);
            oldProduct.setName(product.getName());
            oldProduct.setPrice(product.getPrice());
            oldProduct.setQuantity(product.getQuantity());
            oldProduct.setCategory(product.getCategory());
        }else {
            Product newProduct = new Product(getNewID(),product.getName(),product.getPrice(),product.getQuantity(),product.getCategory());
            productList.add(newProduct);
        }

    }

    @Override
    public void remove(Integer id) {
        productList.remove(findIndexByID(id));
    }

    @Override
    public int findIndexByID(Integer id) {
        return productList.stream().map(Product::getId).toList().indexOf(id);
    }

    @Override
    public List<Product> findAll() {
        return List.of();
    }

    @Override
    public Integer getNewID() {
        Optional<Product> optionalProduct= productList.stream().max(Comparator.comparing(Product::getId));
        if(optionalProduct.isPresent()){
            return optionalProduct.get().getId()+1;
        }
        return 1;
    }
}
