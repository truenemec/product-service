package com.demo.productservice.payments.service.config;

import com.jcraft.jsch.ChannelSftp;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.integration.sftp.session.SftpRemoteFileTemplate;

@Configuration
public class SftpConfig {
    public SftpRemoteFileTemplate sftpRemoteFileTemplate(){
        DefaultSftpSessionFactory factory = new DefaultSftpSessionFactory(true);
        factory.setHost("localhost");
        factory.setPort(22);
        factory.setUser("foo");
        factory.setPassword("foo");
        factory.setAllowUnknownKeys(true);
        SessionFactory<ChannelSftp.LsEntry> sessionFactory = new CachingSessionFactory<>(factory);
        return new SftpRemoteFileTemplate(sessionFactory);
    }
}
