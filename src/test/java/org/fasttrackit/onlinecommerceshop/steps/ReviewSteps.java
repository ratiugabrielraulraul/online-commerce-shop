package org.fasttrackit.onlinecommerceshop.steps;

import org.fasttrackit.onlinecommerceshop.domain.Review;
import org.fasttrackit.onlinecommerceshop.service.ReviewService;
import org.fasttrackit.onlinecommerceshop.transfer.review.SaveReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@Component
public class ReviewSteps {

    @Autowired
    private ReviewService reviewService;

    public Review createReview() {
        SaveReviewRequest request = new SaveReviewRequest();
        request.setContent("Great NoteBook");


        Review review = reviewService.createReview(request);
        assertThat(review, notNullValue());

        assertThat(review.getId(),greaterThan(0L));
        assertThat(review.getContent(),is(review.getContent()));
        assertThat(review.getContent(),is(review.getCreatedAt()));

       return review;

    }

}
