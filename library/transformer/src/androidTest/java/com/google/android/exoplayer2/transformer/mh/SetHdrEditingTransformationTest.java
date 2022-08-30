/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.exoplayer2.transformer.mh;

import static com.google.android.exoplayer2.transformer.AndroidTestUtil.MP4_ASSET_1080P_1_SECOND_HDR10_VIDEO_SDR_CONTAINER;
import static com.google.android.exoplayer2.transformer.AndroidTestUtil.recordTestSkipped;

import android.content.Context;
import android.net.Uri;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.transformer.TransformationRequest;
import com.google.android.exoplayer2.transformer.Transformer;
import com.google.android.exoplayer2.transformer.TransformerAndroidTestRunner;
import com.google.android.exoplayer2.util.Util;
import org.junit.Test;
import org.junit.runner.RunWith;

/** {@link Transformer} instrumentation test for applying an HDR frame edit. */
@RunWith(AndroidJUnit4.class)
public class SetHdrEditingTransformationTest {
  @Test
  public void transformUnexpectedColorInfo() throws Exception {
    Context context = ApplicationProvider.getApplicationContext();
    if (Util.SDK_INT < 29) {
      recordTestSkipped(
          context,
          "SetHdrEditingTransformationTest",
          /* reason= */ "Skipping on this API version due to lack of support for"
              + " MediaFormat#getInteger(String, int).");
      return;
    }

    Transformer transformer =
        new Transformer.Builder(context)
            .setTransformationRequest(
                new TransformationRequest.Builder().experimental_setEnableHdrEditing(true).build())
            .build();
    new TransformerAndroidTestRunner.Builder(context, transformer)
        .build()
        .run(
            /* testId= */ "transformUnexpectedColorInfo",
            MediaItem.fromUri(Uri.parse(MP4_ASSET_1080P_1_SECOND_HDR10_VIDEO_SDR_CONTAINER)));
  }
}
