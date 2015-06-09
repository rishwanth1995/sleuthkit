package org.sleuthkit.autopsy.imagegallery.gui;

import com.google.common.eventbus.Subscribe;
import java.util.Collection;
import java.util.logging.Level;
import javafx.application.Platform;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import org.sleuthkit.autopsy.coreutils.Logger;
import org.sleuthkit.autopsy.coreutils.ThreadConfined;
import org.sleuthkit.autopsy.imagegallery.TagUtils;
import org.sleuthkit.autopsy.imagegallery.datamodel.Category;
import org.sleuthkit.autopsy.imagegallery.datamodel.CategoryChangeEvent;
import org.sleuthkit.autopsy.imagegallery.datamodel.CategoryManager;
import org.sleuthkit.autopsy.imagegallery.datamodel.DrawableFile;

/**
 * TODO: extract common interface out of {@link SingleImageView} and
 * {@link MetaDataPane}
 */
public interface DrawableView extends TagUtils.TagListener {

    //TODO: do this all in css? -jm
    static final int CAT_BORDER_WIDTH = 10;

    static final BorderWidths CAT_BORDER_WIDTHS = new BorderWidths(CAT_BORDER_WIDTH);

    static final CornerRadii CAT_CORNER_RADII = new CornerRadii(3);

    static final Border HASH_BORDER = new Border(new BorderStroke(Color.PURPLE, BorderStrokeStyle.DASHED, CAT_CORNER_RADII, CAT_BORDER_WIDTHS));

    static final Border CAT1_BORDER = new Border(new BorderStroke(Category.ONE.getColor(), BorderStrokeStyle.SOLID, CAT_CORNER_RADII, CAT_BORDER_WIDTHS));

    static final Border CAT2_BORDER = new Border(new BorderStroke(Category.TWO.getColor(), BorderStrokeStyle.SOLID, CAT_CORNER_RADII, CAT_BORDER_WIDTHS));

    static final Border CAT3_BORDER = new Border(new BorderStroke(Category.THREE.getColor(), BorderStrokeStyle.SOLID, CAT_CORNER_RADII, CAT_BORDER_WIDTHS));

    static final Border CAT4_BORDER = new Border(new BorderStroke(Category.FOUR.getColor(), BorderStrokeStyle.SOLID, CAT_CORNER_RADII, CAT_BORDER_WIDTHS));

    static final Border CAT5_BORDER = new Border(new BorderStroke(Category.FIVE.getColor(), BorderStrokeStyle.SOLID, CAT_CORNER_RADII, CAT_BORDER_WIDTHS));

    static final Border CAT0_BORDER = new Border(new BorderStroke(Category.ZERO.getColor(), BorderStrokeStyle.SOLID, CAT_CORNER_RADII, CAT_BORDER_WIDTHS));

    Region getBorderable();

    DrawableFile<?> getFile();

    void setFile(final Long fileID);

    Long getFileID();

    @Subscribe
    void handleCategoryChanged(CategoryChangeEvent evt);

    @Override
    void handleTagsChanged(Collection<Long> ids);

    default boolean hasHashHit() {
        try {
            return getFile().getHashHitSetNames().isEmpty() == false;
        } catch (NullPointerException ex) {
            // I think this happens when we're in the process of removing images from the view while
            // also trying to update it? 
            Logger.getLogger(DrawableView.class.getName()).log(Level.WARNING, "Error looking up hash set hits");
            return false;
        }
    }

    static Border getCategoryBorder(Category category) {
        switch (category) {
            case ZERO:
                return CAT0_BORDER;
            case ONE:
                return CAT1_BORDER;
            case TWO:
                return CAT2_BORDER;
            case THREE:
                return CAT3_BORDER;
            case FOUR:
                return CAT4_BORDER;
            case FIVE:
            default:
                return CAT5_BORDER;
        }
    }

    @ThreadConfined(type = ThreadConfined.ThreadType.ANY)
    default Category updateCategoryBorder() {
        final Category category = getFile().getCategory();
        final Border border = hasHashHit() && (category == Category.ZERO)
                ? HASH_BORDER
                : DrawableView.getCategoryBorder(category);

        Platform.runLater(() -> {
            getBorderable().setBorder(border);
        });
        return category;
    }

}
