package ma.yassine.activitepratique2.web;

import ma.yassine.activitepratique2.entities.Product;
import ma.yassine.activitepratique2.repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author pc
 **/
@Controller
public class ProductController {
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    @GetMapping("/index")
    public String products(Model model){
        model.addAttribute("products",productRepository.findAll());
        return "products";
    }


    @GetMapping("/delete")
    public String deleteProduct(Long id){
        productRepository.deleteById(id);
        return "redirect:/index";
    }

    @GetMapping("/formProducts")
    public String formProducts(Model model){
        model.addAttribute("product",new Product());
        return "formProducts";
    }

    @PostMapping("/save")
    public String save(Product product){
        productRepository.save(product);
        return "redirect:/index";
    }
}
