package com.assignment.subscriber.subscriberassignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subscribers implements Serializable {
    private List<Subscriber> subscribers;
}
