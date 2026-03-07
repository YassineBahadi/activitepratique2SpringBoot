package ma.yassine.activitepratique2.web;

import ma.yassine.activitepratique2.repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
