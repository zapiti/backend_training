package br.com.team.appx.convinience.controllers.product;

import br.com.team.appx.convinience.exception.UserInexistenteException;
import br.com.team.appx.convinience.model.Product;
import br.com.team.appx.convinience.model.Role;
import br.com.team.appx.convinience.model.User;
import br.com.team.appx.convinience.model.UserId;
import br.com.team.appx.convinience.security.JwtTokenUtil;
import br.com.team.appx.convinience.service.product.ProductService;
import br.com.team.appx.convinience.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/product/update")
public class ProductController {

    @Autowired
    private UserService userService;


    @Autowired
    private ProductService productService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PutMapping()
    public ResponseEntity<Object> updateProduct(@RequestBody Product product, @RequestHeader String Authorization) throws IOException {
        String token = Authorization.replace("Bearer", "").trim();


        Role role = jwtTokenUtil.getRoleFromToken(token);

        UserId userId = jwtTokenUtil.getUserId(token);

        Boolean isUser = this.userService.verifyExists(userId);

        if(!isUser){
            throw new UserInexistenteException();
        }
        //todo colocar isto
//        if(role == Role.USER){
//            throw new UserInexistenteException("Usuário não autorizado");
//        }

        return this.productService.updateProduct(product,userId);
    }
}