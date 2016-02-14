package madeexercises.MultiFormatCalculator.ui.model;

import madeexercises.MultiFormatCalculator.ui.view.ModelView;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractModel {

    protected List<ModelView> views = new ArrayList<>();

    public void updateViews() {
        views.forEach(ModelView::updateView);
    }
}
