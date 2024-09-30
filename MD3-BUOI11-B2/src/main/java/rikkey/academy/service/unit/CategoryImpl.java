package rikkey.academy.service.unit;

import rikkey.academy.dao.ICategoryDao;
import rikkey.academy.dao.ICategoryImpl;
import rikkey.academy.model.Category;
import rikkey.academy.service.IGenericDesign;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class CategoryImpl implements IGenericDesign<Category,Integer> {
//    public static List<Category> categoryList= new ArrayList<>();
//    static{
//        categoryList.add(new Category(1,"2024",true));
//        categoryList.add(new Category(2,"2023",true));
//    }
    private  static final ICategoryDao categoryDao =new ICategoryImpl();



@Override
public void addAndUpdate(Category category) {
    // Get the ID of the category
    Integer id = category.getId();

    // If the ID is null, it means this is a new category, so we assign a new ID
    if (id == null) {
        // Generate new ID for the new category
        Category newCategory = new Category(null,category.getName(), category.getStatus());
        // Add the new category to the database
        categoryDao.addAndUpdate(newCategory);
    } else {
        // Check if the category already exists in the system
        int index = findIndexByID(id);

        if (index != -1) {
            // If the category exists, update the existing category with new data
            Category existingCategory = findAll().get(index);
            existingCategory.setName(category.getName());
            existingCategory.setStatus(category.getStatus());
            // Update the category in the database
            categoryDao.addAndUpdate(existingCategory);
        } else {
            // If the category does not exist but has an ID, treat it as new and add it
            Category newCategory = new Category(id, category.getName(), category.getStatus());
            categoryDao.addAndUpdate(newCategory);
        }
    }
}





    @Override
    public void remove(Integer id) {
        categoryDao.remove(id);

    }

    @Override
    public int findIndexByID(Integer id) {
        return categoryDao.findIndexByID(id);
    }

    @Override
    public Integer getNewID() {
        return 1;
    }


    @Override
    public List<Category> findAll() {
        return  categoryDao.findAll() ;
    }
}
