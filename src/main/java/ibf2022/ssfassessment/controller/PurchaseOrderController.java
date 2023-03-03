package ibf2022.ssfassessment.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ibf2022.ssfassessment.model.Item;
import ibf2022.ssfassessment.model.ShippingForm;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;


@Controller
@RequestMapping(path="/", produces = MediaType.TEXT_HTML_VALUE)
public class PurchaseOrderController {
    

    @PostMapping("/add")
    public String addItem(@RequestBody MultiValueMap<String, String> form,
    Model model, HttpSession sess) {
        List<Item> cartItems = (List<Item>)sess.getAttribute("cart");
        if(null == cartItems) {
            cartItems = new LinkedList();
            sess.setAttribute("cart", cartItems);
        }
        String item =form.getFirst("name");
        Integer quantity =Integer.parseInt(form.getFirst("quantity"));
        cartItems.add(new Item(item, quantity));

        //Create new order
        ShippingForm shippingForm = new ShippingForm();
        shippingForm.setCname(form.getFirst("cname"));
        shippingForm.setAddress(form.getFirst("address"));
        shippingForm.setCartItems(cartItems);

        sess.setAttribute("checkoutCart", shippingForm);
        model.addAttribute("cartItems", cartItems);
        return "view1";
    }

    @GetMapping("/shippingaddress")
    public String getShippingAddress(Model model){
        ShippingForm shippingForm = new ShippingForm();
        model.addAttribute("sf", shippingForm);
        return "view2";
    }

    @PostMapping("/checkout")
    public String getShippingAddress(@Valid ShippingForm sf, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "view2";
        }
        model.addAttribute("shippingForm", sf);
        return "view3";
    }

}
