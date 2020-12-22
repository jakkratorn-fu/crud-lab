package com.allianz.hello;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.allianz.hello.modellab.BankAccount;
import com.allianz.hello.modellab.BankAccountRepository;

@RestController
public class SimpleController {
	@Autowired
	private BankAccountRepository bankAccountRepository;

	@RequestMapping("/")
	   @ResponseBody
	   String home() {
	       return "Hello World!";
	   }
 	   
	   @RequestMapping("/bank")
	   public Iterable<BankAccount> getAllAccount() {
		   Iterable<BankAccount> acc = bankAccountRepository.findAll();
			for (BankAccount col : acc) {
				System.out.println(col);
			}
		   return acc;
	   }
	   
	   @RequestMapping("/add")
	   public void addAccount() {
			BankAccount account = new BankAccount("Fu", "Test", new BigDecimal(10000), 0);
			bankAccountRepository.save(account);
	   }
	   
	   @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	   public void deleteAccount(@PathVariable String id) {
		   bankAccountRepository.deleteById(id);
	   }
	   
	   @GetMapping("/get/{id}")
	   public  Optional<BankAccount> getById(@PathVariable String id) {
		   return bankAccountRepository.findById(id);
	   }
	   
	   @PutMapping("/update/{id}")
	   public void updateById(@PathVariable String id, @RequestBody BankAccount account) {
		   
		   BankAccount acc = bankAccountRepository.findById(id).get();
		   acc.setBalance(account.getBalance());
		   acc.setPersonId(account.getPersonId());
		   acc.setStatus(account.getStatus());
		   
		   bankAccountRepository.save(acc);
	   }
}
