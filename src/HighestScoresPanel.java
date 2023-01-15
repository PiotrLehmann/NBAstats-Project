import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;


/**
 * HighestScoresPanel class acts like JPanel, and it is used to create
 * HighestScoresPanel and displaying bar chart on it
 */
public class HighestScoresPanel extends JPanel
{
    /**
     * panel for bars on chart
     */
    private final JPanel barPanel;

    /**
     * panel for labels on chart
     */
    private final JPanel labelPanel;

    /**
     * list of Bar type objects
     */
    private final List<Bar> bars = new ArrayList<>();

    public HighestScoresPanel()
    {
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new BorderLayout());

        int barGap = 10;
        barPanel = new JPanel( new GridLayout(1, 0, barGap, 0) );
        Border outer = new MatteBorder(1, 1, 1, 1, Color.BLACK);
        Border inner = new EmptyBorder(10, 10, 0, 10);
        Border compound = new CompoundBorder(outer, inner);
        barPanel.setBorder(compound);

        labelPanel = new JPanel( new GridLayout(1, 0, barGap, 0) );
        labelPanel.setBorder( new EmptyBorder(5, 10, 0, 10) );

        add(barPanel, BorderLayout.CENTER);
        add(labelPanel, BorderLayout.PAGE_END);
    }

    /**
     * adds column to a chart
     * @param label contains text for label for specific Bar object
     * @param value contains value for displaying above Bar Object
     * @param color contains color of the Bar object
     */
    public void addHistogramColumn(String label, int value, Color color)
    {
        Bar bar = new Bar(label, value, color);
        bars.add(bar);
    }

    /**
     * responsible for histogram layout
     */
    public void layoutHistogram()
    {
        barPanel.removeAll();
        labelPanel.removeAll();

        int maxValue = 0;

        for (Bar bar: bars)
            maxValue = Math.max(maxValue, bar.value());

        for (Bar bar: bars)
        {
            JLabel label = new JLabel(bar.value() + "");
            label.setHorizontalTextPosition(JLabel.CENTER);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalTextPosition(JLabel.TOP);
            label.setVerticalAlignment(JLabel.BOTTOM);
            int histogramHeight = 400;
            int barHeight = (bar.value() * histogramHeight) / maxValue;
            int barWidth = 80;
            Icon icon = new ColorIcon(bar.color(), barWidth, barHeight);
            label.setIcon(icon);
            barPanel.add(label);

            JLabel barLabel = new JLabel(bar.label());
            barLabel.setHorizontalAlignment(JLabel.CENTER);
            labelPanel.add(barLabel);
        }
    }

    private record Bar(String label, int value, Color color) {
    }

    /**
     * creates shadow behind the bars
     * @param color contains color name of shadow
     * @param width contains width value of shadow
     * @param height contains height value of shadow
     */
    private record ColorIcon(Color color, int width, int height) implements Icon {

        public int getIconWidth() {
                return width;
            }

            public int getIconHeight() {
                return height;
            }

            public void paintIcon(Component c, Graphics g, int x, int y) {
                g.setColor(color);
                int shadow = 3;
                g.fillRect(x, y, width - shadow, height);
                g.setColor(Color.GRAY);
                g.fillRect(x + width - shadow, y + shadow, shadow, height - shadow);
            }
        }
}
