/*
 * Copyright 2024 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package vertexai.gemini;

// [START generativeaionvertexai_gemini_video_with_audio]

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.ContentMaker;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.PartMaker;
import com.google.cloud.vertexai.generativeai.ResponseHandler;
import java.io.IOException;

public class VideoInputWithAudio {

  public static void main(String[] args) throws IOException {
    // TODO(developer): Replace these variables before running the sample.
    String projectId = "your-google-cloud-project-id";
    String location = "us-central1";
    String modelName = "gemini-2.0-flash-001";

    videoAudioInput(projectId, location, modelName);
  }

  // Analyzes the given video input, including its audio track.
  public static String videoAudioInput(String projectId, String location, String modelName)
      throws IOException {
    // Initialize client that will be used to send requests. This client only needs
    // to be created once, and can be reused for multiple requests.
    try (VertexAI vertexAI = new VertexAI(projectId, location)) {
      String videoUri = "gs://cloud-samples-data/generative-ai/video/pixel8.mp4";

      GenerativeModel model = new GenerativeModel(modelName, vertexAI);
      GenerateContentResponse response = model.generateContent(
          ContentMaker.fromMultiModalData(
              "Provide a description of the video.\n The description should also "
                  + "contain anything important which people say in the video.",
              PartMaker.fromMimeTypeAndData("video/mp4", videoUri)
          ));

      String output = ResponseHandler.getText(response);
      System.out.println(output);

      return output;
    }
  }
}
// [END generativeaionvertexai_gemini_video_with_audio]
