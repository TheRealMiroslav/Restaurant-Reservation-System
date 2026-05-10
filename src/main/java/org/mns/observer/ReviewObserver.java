package org.mns.observer;

import org.mns.model.Review;

public interface ReviewObserver {
    void onReviewCreated(Review review);
}
