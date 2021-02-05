package no.hvl.dat110.messaging;

import java.util.Arrays;

import no.hvl.dat110.TODO;

public class Message {

	private byte[] payload;

	public Message(byte[] payload) {
		if (payload.length > 127) {
			byte[] fixedPayload = new byte[127]; // New array which will have correct length
			for (int i = 0; i<127; i++) {
				fixedPayload[i] = payload[i];
			}
			this.payload = fixedPayload;
		} else {
			this.payload = payload;
		}
	}

	public Message() {
		super();
	}

	public byte[] getData() {
		return this.payload; 
	}

	public byte[] encapsulate() {
		
		int length = getData().length;
		byte[] encoded = new byte[128];
		encoded[0] = (byte) length;

		for (int i = 0; i< length; i++) {
			encoded[i+1] = getData()[i];
		}

		return encoded;
		
	}

	public void decapsulate(byte[] received) {
		int length = received[0];
		this.payload = new byte[length];
		for (int i = 1; i<length+1; i++) {
			this.payload[i-1] = received[i];
		}
		
	}
}
