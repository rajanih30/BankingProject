package com.firstspring.Banking.controller;



import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;




import com.firstspring.Banking.entity.customer;
import com.firstspring.Banking.entity.login;
import com.firstspring.Banking.entity.transaction;
import com.firstspring.Banking.service.customerservices;
import com.firstspring.Banking.service.transactionservices;
import com.firstspring.Banking.entity.fundtransfer;




@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class customer_controller {

@Autowired
private customerservices cs;//because controller depends on service layer

@Autowired
private transactionservices ts;//because controller depends on service layer




@PostMapping("/customer")
public customer Save (@RequestBody customer c)
{
return cs.Save(c);
}



@PutMapping("/customer")//dont ever have same mapping with same parameters passed.
public customer Update (@RequestBody customer c)
{
return cs.Update(c);
}



@DeleteMapping("/customer/{id}")
public void Delete (@PathVariable Long id)//pathvariable annotation is used whenever you pass nmber in url
{
cs.Delete(id);
}




@GetMapping("/customer/{id}")
public Optional<customer> getone (@PathVariable Long id)
{
return cs.getone(id);
}



@GetMapping("/customer")
public List<customer> getall ()
{
return cs.getall();
}
@PostMapping("/login")
public customer authentication (@RequestBody login l)
{
return cs.authenticate(l);
}



@PostMapping("/deposite")
public boolean deposite (@RequestBody transaction t)
{
return ts.deposite(t);
}



@PostMapping("/withdraw")
public String withdraw (@RequestBody transaction t)
{
return ts.withdraw(t);
}



@PostMapping("/fundtransfers")
public String fundtransfer(@RequestBody fundtransfer trans) {
return ts.fundtransfer(trans.getTransactions());
}



@GetMapping("/transaction/{accountNum}")
public List<transaction> getAllTransactions(@PathVariable String accountNum) {
return ts.getAll(accountNum);
}
}

