package br.com.lovizoto.externalapi.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MessageLog implements Serializable {

    private String sessionId;
    private String rawContent;
    private String parsed_content;
    private String direction; //INCOMING or OUTGOING
    private String source;
    private LocalDateTime timestamp;


    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getRawContent() {
        return rawContent;
    }

    public void setRawContent(String rawContent) {
        this.rawContent = rawContent;
    }

    public String getParsed_content() {
        return parsed_content;
    }

    public void setParsed_content(String parsed_content) {
        this.parsed_content = parsed_content;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
