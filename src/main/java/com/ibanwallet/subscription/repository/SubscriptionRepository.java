package com.ibanwallet.subscription.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibanwallet.subscription.entity.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long>{


	
}
