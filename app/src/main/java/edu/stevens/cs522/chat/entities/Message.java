package edu.stevens.cs522.chat.entities;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

import edu.stevens.cs522.base.DateUtils;
import edu.stevens.cs522.chat.rest.client.Exclude;

/**
 * Created by dduggan.
 */

// TODO annotate (including FK constraints)
@Entity(
        foreignKeys = @ForeignKey(
                entity=Peer.class, onDelete=ForeignKey.CASCADE,
                parentColumns="id", childColumns="senderId"),
        indices = {@Index("senderId")})
public class Message implements Parcelable {

    // Primary key in the database
    @PrimaryKey(autoGenerate = true)
    public long id;

    // Global id provided by the server
    public long seqNum;

    public String chatRoom;

    public String messageText;

    @TypeConverters(DateConverter.class)
    public Date timestamp;

    public Double latitude;

    public Double longitude;

    public String sender;

    public long senderId;

    public Message() {
    }

    public Message(Parcel in) {
        // TODO
        id = in.readLong();
        seqNum = in.readLong();
        chatRoom = in.readString();
        messageText = in.readString();
        timestamp = DateUtils.readDate(in);
        latitude = in.readDouble();
        longitude = in.readDouble();
        sender = in.readString();
        senderId = in.readLong();
    }

    @Override
    public String toString() {
        return messageText;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO
        dest.writeLong(id);
        dest.writeLong(seqNum);
        dest.writeString(chatRoom);
        dest.writeString(messageText);
        DateUtils.writeDate(dest,timestamp);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(sender);
        dest.writeLong(senderId);
    }

    public static final Creator<Message> CREATOR = new Creator<Message>() {

        @Override
        public Message createFromParcel(Parcel source) {
            // TODO
            return new Message(source);
        }

        @Override
        public Message[] newArray(int size) {
            // TODO
            return new Message[size];
        }

    };

}