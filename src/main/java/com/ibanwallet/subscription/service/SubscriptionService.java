package com.ibanwallet.subscription.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.ibanwallet.subscription.entity.Subscription;
import com.ibanwallet.subscription.repository.SubscriptionRepository;


@Component
public class SubscriptionService {
	

	private SubscriptionRepository subscriptionRepository;

	public SubscriptionService(SubscriptionRepository subscriptionRepository) {
		this.subscriptionRepository = subscriptionRepository;
	}
	public List<Subscription> selectAllSubscriptors() {
		return subscriptionRepository.findAll();
	}
	public Optional<Subscription> selectSubscriptionById(Long id){
		return subscriptionRepository.findById(id);
		
	}
	public Subscription updateSubscription(Subscription subscription) {
		return subscriptionRepository.save(subscription);
	}
	public void deleteSubscriptionById(Long id) {
		Subscription subscription = subscriptionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid subscription Id:" + id));
		subscriptionRepository.delete(subscription);
	}
	
	public Subscription insertSubscription(Subscription subscription){
		return subscriptionRepository.save(subscription);
		
		
	}

}
