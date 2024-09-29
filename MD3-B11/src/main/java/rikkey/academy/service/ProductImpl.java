package rikkey.academy.service;

import rikkey.academy.model.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ProductImpl implements IgenericService<Product,Integer> {
    public static List<Product> productList = new ArrayList<>();
    static {
       productList.add(new Product(1,"Thuốc lá",23.0,"Say rồi","Analdi",true));
        productList.add(new Product(2,"Thuốc lắc",10.0,"Say rồi","Vcl",true));
    }
    @Override
    public void addAndUpdate(Product product) {
//        if (product.getId() != null) {
            int index = findIndexByID(product.getId());
            if (index != -1) {
                // Cập nhật thông tin sản phẩm hiện tại
                Product existingProduct = productList.get(index);
                existingProduct.setName(product.getName());
                existingProduct.setDescription(product.getDescription());
                existingProduct.setPrice(product.getPrice());
                existingProduct.setProducer(product.getProducer());
                existingProduct.setStatus(product.isStatus());
                productList.set(index, existingProduct);
            } else {
                // Nếu ID không tồn tại, thêm sản phẩm mới
                Product newProduct = new Product(getNewID(), product.getName(), product.getPrice(), product.getDescription(), product.getProducer(), product.isStatus());
                productList.add(newProduct);
            }
//        } else {
//            // Nếu ID là null, thêm sản phẩm mới
//            Product newProduct = new Product(getNewID(), product.getName(), product.getPrice(), product.getDescription(), product.getProducer(), product.isStatus());
//            productList.add(newProduct);
//        }


    }

    @Override
    public void delete(Integer id) {
     productList.remove(findIndexByID(id));
    }



    @Override
    public int findIndexByID(Integer id) {
      return productList.stream().map(Product::getId).toList().indexOf(id);

    }

    @Override
    public Integer getNewID() {
        Optional<Product> productOptional = productList.stream().max(Comparator.comparing(Product::getId));
        if (productOptional.isPresent()) {
            return productOptional.get().getId()+1;
        }
        return 1;
    }
}
