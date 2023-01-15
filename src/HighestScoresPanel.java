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

public class HighestScoresPanel extends JPanel
{
    private final JPanel barPanel;
    private final JPanel labelPanel;
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

    public void addHistogramColumn(String label, int value, Color color)
    {
        Bar bar = new Bar(label, value, color);
        bars.add(bar);
    }

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
