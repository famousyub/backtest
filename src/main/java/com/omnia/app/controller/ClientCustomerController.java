package com.omnia.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.omnia.app.entity.Greeting;
import com.omnia.app.entity.HelloMessage;
import com.omnia.app.model.Area;
import com.omnia.app.model.Company;
import com.omnia.app.model.Order;
import com.omnia.app.model.OrderProduct;
import com.omnia.app.model.OrderStatus;
import com.omnia.app.model.Recipe;
import com.omnia.app.payload.CompanyListResponse;
import com.omnia.app.payload.OrderProductDto;
import com.omnia.app.repository.AreaRepository;
import com.omnia.app.repository.CompanyRepository;
import com.omnia.app.repository.RecipeRepository;
import com.omnia.app.response.RestoResponse;
import com.omnia.app.service.IAreaService;
import com.omnia.app.service.MyRecipeService;
import com.omnia.app.service.OrderProductService;
import com.omnia.app.service.OrderService;
import com.omnia.app.service.RecipeService;
import com.omnia.app.service.Restarauntservice;
import com.omnia.app.util.CompanyMapper;

@RestController
@RequestMapping("/api/customer")
public class ClientCustomerController {
	
	
	 public static class OrderForm {

	        private List<OrderProductDto> productOrders;

	        public List<OrderProductDto> getProductOrders() {
	            return productOrders;
	        }

	        public void setProductOrders(List<OrderProductDto> productOrders) {
	            this.productOrders = productOrders;
	        }
	    }
	
	@Autowired
	Restarauntservice resserv;
	
	@Autowired
	CompanyRepository comorepo;
	
	
	
	@Autowired
	IAreaService iareaservice ;
	
	@Autowired
	AreaRepository arepo;
	
	@Autowired
	OrderService orderService;

	@Autowired
	    OrderProductService orderProductService;
	
	
	@Autowired
	MyRecipeService reserv;
	
	
	@Autowired
	RecipeRepository reciperepo;
	
	
	
	@MessageMapping("/hello")
	  @SendTo("/topic/greetings")
	  public Greeting greeting(HelloMessage message) throws Exception {
	    Thread.sleep(1000); // simulated delay
	    return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
	  }

	
	
	@PostMapping("/psorder")
	 public ResponseEntity<Order> create(@RequestBody OrderForm form) {
        List<OrderProductDto> formDtos = form.getProductOrders();
        //validateProductsExistence(formDtos);
        Order order = new Order();
        order.setStatus(OrderStatus.PAID.name());
        order = this.orderService.create(order);

        List<OrderProduct> orderProducts = new ArrayList<>();
        for (OrderProductDto dto : formDtos) {
            orderProducts.add(orderProductService.create(new OrderProduct(order, reserv.getProrecipe(dto
              .getProduct()
              .getId()), dto.getQuantity())));
        }

        order.setOrderProducts(orderProducts);

        this.orderService.update(order);

        String uri = ServletUriComponentsBuilder
          .fromCurrentServletMapping()
          .path("/orders/{id}")
          .buildAndExpand(order.getId())
          .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        return new ResponseEntity<>(order, headers, HttpStatus.CREATED);
    }
	
	@GetMapping("/companies")
	
	public ResponseEntity<?>  getAllcmpanies(){
		
		 List<Company>  allcompanies = comorepo.findAll();
		 
			List<CompanyListResponse> companyListResponse = allcompanies.stream().map(company -> {
				// Employee employee = userRepository.findById(company.getUpdatedBy())
				// .orElseThrow(() -> new ResourceNotFoundException("User", "id",
				// company.getUpdatedBy()));
				return CompanyMapper.mapCompanyToCompanyListResponse(company);
			}).collect(Collectors.toList());

		
		 return ResponseEntity.ok().body(companyListResponse);
	}
	@GetMapping("/all/{compId}")
	public ResponseEntity<?>  getRestoAll(@PathVariable("compId") Long comId){
		List<RestoResponse>  allresto =  resserv.getRestoswitcher(comId);
		
		return  ResponseEntity.ok().body(allresto);
		
		
	}
	
	@GetMapping("/allresto/{compId}/{RestoId}")
	public ResponseEntity<?>  getRestoByid(@PathVariable("compId") Long comId , @PathVariable("RestoId") Long RestoId){
		RestoResponse rt = resserv.getRestoById(comId, RestoId);
		
		return ResponseEntity.ok().body(rt);
		
		
		
		
	}
	
	
@GetMapping("/listareasp/{compId}")
	
	public ResponseEntity<?> getAreaslisp(@PathVariable("compId") Long compId){
		
		List<Area> areas =arepo.getAreabyId(compId);
		
		
		
		return ResponseEntity.ok().body(areas);
		
		
		
	}


@GetMapping("/listarea")
List<Area>  getAllArea(){
	return  iareaservice.getAllArea();
}

@GetMapping("/cool-recipes")
public Collection<Recipe> coolCars() {
    return reciperepo.findAll().stream()
            .filter(this::isCool)
            .collect(Collectors.toList());
}

private boolean isCool(Recipe car) {
    return !car.getName().contains("spicy")&&
    		!car.getName().contains("chimique") &&
            !car.getName().contains("caramel") &&
            !car.getName().contains("choclate");
    		
           
}



	
	

}
