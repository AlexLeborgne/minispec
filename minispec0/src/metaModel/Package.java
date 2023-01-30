package metaModel;

import java.util.ArrayList;
import java.util.List;

public class Package implements MinispecElement {
    private String packageName;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
