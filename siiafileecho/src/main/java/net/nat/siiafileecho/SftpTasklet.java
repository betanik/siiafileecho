package net.nat.siiafileecho;


import java.io.File;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.support.MessageBuilder;

public class SftpTasklet implements Tasklet {

 private String fileName;
 private MessageChannel sftpChannel;

 @Override
 public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

   File file = new File(fileName);
   if (file.exists()) {
     Message message = MessageBuilder.withPayload(file).build();
     sftpChannel.send(message);
   } else {
     System.out.println("File does not exist.");
   }
   return RepeatStatus.FINISHED;
 }

 public String getFileName() {
   return fileName;
 }

 public void setFileName(String fileName) {
   this.fileName = fileName;
 }

 public MessageChannel getSftpChannel() {
   return this.sftpChannel;
 }

 public void setSftpChannel(MessageChannel sftpChannel) {
   this.sftpChannel = sftpChannel;
 }
}