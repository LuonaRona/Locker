package cn.xpbootcamp.locker;

public class StoreResults {
    private boolean status;
    private String type;
    private String content;

    public StoreResults(boolean status, String type, String content) {
      this.status = status;
      this.type = type;
      this.content = content;
    }

    public boolean getStatus() {
      return this.status;
    }

    public String getContent() {
      return this.content;
    }

    public String getType() {
      return this.type;
    }
}
