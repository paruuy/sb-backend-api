package com.ibanwallet.subscription.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibanwallet.subscription.dto.SubscriptionDTO;
import com.ibanwallet.subscription.entity.Subscription;
import com.ibanwallet.subscription.service.SubscriptionService;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

	SubscriptionService subscriptionService;

	Mapper mapper;

	public SubscriptionController(SubscriptionService subscriptionService, Mapper mapper) {
		this.subscriptionService = subscriptionService;
		this.mapper = mapper;
	}

	@GetMapping
	public List<SubscriptionDTO> findAll() {

		List<Subscription> subscriptions = subscriptionService.selectAllSubscriptors();
		return subscriptions.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Subscription> findById(@PathVariable Long id) {
		return subscriptionService.selectSubscriptionById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody @Valid SubscriptionDTO subscriptionDTO) {
		try {
			Subscription savedSubscription = subscriptionService.insertSubscription(convertToEntity(subscriptionDTO));
			return new ResponseEntity<>(convertToDto(savedSubscription), HttpStatus.CREATED);
		} catch (Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody SubscriptionDTO subscriptionDTO) {
		 return subscriptionService.selectSubscriptionById(id).map(record -> {
			 	record.setConsentFlag(subscriptionDTO.isConsentFlag());
			 	record.setFirstName(subscriptionDTO.getFirstName());
			 	record.setDateOfBirth(subscriptionDTO.getDateOfBirth());
			 	record.setEmail(subscriptionDTO.getEmail());
			 	record.setGender(subscriptionDTO.getGender());
			 	Subscription subscriptionUpdated = subscriptionService.updateSubscription(record);
				return ResponseEntity.ok().body(subscriptionUpdated);
			}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return subscriptionService.selectSubscriptionById(id).map(record -> {
			subscriptionService.deleteSubscriptionById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

	private SubscriptionDTO convertToDto(Subscription subscription) {
		if (subscription == null) {
			new IllegalArgumentException("The subscription doesn't exist");
		}
		SubscriptionDTO subscriptionDTO = mapper.map(subscription, SubscriptionDTO.class);
		return subscriptionDTO;
	}

	private Subscription convertToEntity(SubscriptionDTO subscriptionDTO) {
		Subscription subscription = mapper.map(subscriptionDTO, Subscription.class);
		return subscription;
	}

}
