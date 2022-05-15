package ru.gb.gbthymeleaf.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.gb.api.order.dto.OrderDto;
import ru.gb.gbthymeleaf.service.OrderService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/all")
    public String getProductList(Model model, @CookieValue(value = "jwt") String jwt) {
        model.addAttribute("orders", orderService.findAll(jwt));
        return "order-list";
    }

        @GetMapping("/{orderId}")
        public String info(Model model, @PathVariable(name = "orderId") Long id) {
            OrderDto orderDto;
            if (id != null) {
                orderDto = orderService.findById(id);
            } else {
                return "redirect:/product/all";
            }
            model.addAttribute("order", orderDto);
            return "order-info";
        }


        @GetMapping
        public String showForm(Model model, @RequestParam(name = "id", required = false) Long id) {
            OrderDto orderDto;
            if (id != null) {
                orderDto = orderService.findById(id);
            } else {
                orderDto = new OrderDto();
            }
            model.addAttribute("order", orderDto);
            return "order-form";
        }


        @PostMapping
        public String saveOrder(OrderDto orderDto, @CookieValue(value = "jwt") String jwt) {
            orderService.save(orderDto, jwt);
            return "redirect:/order/all";
        }


        @GetMapping("/delete")
        public String deleteById(@RequestParam(name = "id") Long id) {
            orderService.deleteById(id);
            return "redirect:/order/all";
        }

}
