package ma.yassine.activitepratique2.web;

import jakarta.validation.Valid;
import ma.yassine.activitepratique2.entities.Product;
import ma.yassine.activitepratique2.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String products(Model model,
                           @RequestParam(name="page",defaultValue = "0") int page,
                           @RequestParam(name="size",defaultValue = "5") int size,
                           @RequestParam(name="keyword",defaultValue = "") String keyword
                           ){
        Page<Product> pageProducts=productRepository.findByNameContains(keyword, PageRequest.of(page,size));

        model.addAttribute("products",pageProducts.getContent());
        model.addAttribute("pages",new int[pageProducts.getTotalPages()]);
        model.addAttribute("keyword",keyword);
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
    public String save(@Valid Product product, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "formProducts";
        }
        productRepository.save(product);
        return "redirect:/index";
    }

    @GetMapping("/editProduct")
    public String editProduct(Model model,Long id){
        Product product=productRepository.findById(id).get();
        model.addAttribute("product",product);
        return "formProducts";
    }
}
