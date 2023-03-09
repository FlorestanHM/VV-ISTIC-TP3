package fr.istic.vv;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TLSSocketFactoryTestMocks {
    TLSSocketFactory factory;
    SSLSocket sslSocket;

    @BeforeEach
    void init() {
        factory = new TLSSocketFactory();
        sslSocket = mock(SSLSocket.class);
    }

    @Test
    public void preparedSocket_NullProtocols()  {
        when(sslSocket.getEnabledProtocols()).thenReturn(null);
        when(sslSocket.getSupportedProtocols()).thenReturn(null);

        factory.prepareSocket(sslSocket);

        verify(sslSocket, times(1)).getEnabledProtocols();
        verify(sslSocket, times(1)).getSupportedProtocols();
        verify(sslSocket, never()).setEnabledProtocols(any());
    }

    @Test
    public void typical()  {
        when(sslSocket.getSupportedProtocols()).thenReturn(shuffle(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"}));
        when(sslSocket.getEnabledProtocols()).thenReturn(shuffle(new String[]{"SSLv3", "TLSv1"}));

        factory.prepareSocket(sslSocket);

        verify(sslSocket, times(1)).getEnabledProtocols();
        verify(sslSocket, times(1)).getSupportedProtocols();
        verify(sslSocket, times(1)).setEnabledProtocols(new String[] {"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3" });
    }

    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }

}