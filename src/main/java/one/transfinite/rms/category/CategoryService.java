package one.transfinite.rms.category;

import one.transfinite.rms.execption.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategory(){
        return this.categoryRepository.findAll();
    }

    public Category getCategoryById(Long categoryId) {
        return this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category does not exists"));
    }

    public Category addCategory(Category category) {

        return this.categoryRepository.save(category);
    }

    public void updatwCategory(Category category){
        this.categoryRepository.findById(category.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category does not exists"));
        this.categoryRepository.save(category);
    }

    public void deleteCategory(Long categoryId) {
        this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category does not exists"));
        this.categoryRepository.deleteById(categoryId);
    }
}
